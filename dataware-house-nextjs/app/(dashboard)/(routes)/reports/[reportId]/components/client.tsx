"use client";

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { TOKEN_COOKIES } from "@/constants/api-endpoints";
import { getToken } from "@/lib/init-token";
import {
  metaDatas,
  reportColumns,
  reportParams,
  reportRoles,
  reports,
} from "@prisma/client";
import axios from "axios";
import {
  ArrowLeftRight,
  ArrowRight,
  BarChart3,
  ChevronRight,
  Download,
  Home,
  Loader2,
  X,
} from "lucide-react";
import Link from "next/link";
import React, { useEffect, useRef, useState } from "react";
import moment from "moment";
import { Button } from "@/components/ui/button";
import { cn } from "@/lib/utils";
import { Calendar as CalendarIcon } from "lucide-react";
import { Calendar } from "@/components/ui/calendar";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import { format } from "date-fns";
import { SelectOption, SelectedOption, SnSelect } from "@/components/sn-select";
import toast from "react-hot-toast";

import Excel from "exceljs";
import Pagination from "@/components/pagination/pagination";
import ErrorDialog from "./error-dialog";
import {
  getAllReportService,
  getTotalReportService,
} from "@/service/report-service";
import Toast from "./show-toast";
import ErrorDialogReport from "./error-dialog-report";
import { tr } from "date-fns/locale";

interface ReportClientProps {
  report: reports | null;
  reportRoles: reportRoles[];
  reportColumns: reportColumns[];
  reportParams: reportParams[];
  metaBranchs: metaDatas[];
  metaCurrencies: metaDatas[];
  metaNationlities: metaDatas[];
}

export const ReportClient: React.FC<ReportClientProps> = ({
  report,
  reportRoles,
  reportColumns,
  reportParams,
  metaBranchs,
  metaCurrencies,
  metaNationlities,
}) => {
  const [search, setSearch] = useState("");
  const [reports, setReports] = useState([]);
  const [show, setShow] = useState(10);
  const [showOpt, setShowOpt] = useState(false);
  const [query, setQuery] = useState("");
  const [branch, setBranch] = useState([""]);
  const [ccy, setCcy] = useState([""]);
  const [reportDate, setReportDate] = React.useState<Date>();
  const [branchs, setBranchs] = useState<SelectOption[]>([]);
  const [currency, setCurrency] = useState<SelectOption[]>([]);
  const [loading, setLoading] = useState(false);
  const [loadingReport, setLoadingReport] = useState(false);
  const [paramSize, setParamSize] = useState(0);
  const [fromDate, setFromDate] = React.useState<Date>();
  const [toDate, setToDate] = React.useState<Date>();
  const [stickyClass, setStickyClass] = useState("");
  const [metadata, setMetadata] = useState<metaDatas[]>([]);
  const [national, setNational] = useState([""]);
  const [nationality, setNationality] = useState<SelectOption[]>([]);
  const [trxType, setTrxType] = useState("");
  const [acctNo, setAcctNo] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const [modalMessage, setModalMessage] = useState(false);
  const [modalExcelMessage, setModalExcelMessage] = useState(false);
  const [totalRecord, setTotalRecord] = useState(0);
  const [showToast, setShowToast] = useState(false); // Toast visibility state
  const [progress, setProgress] = useState(0); // Progress state
  const [remainingTime, setRemainingTime] = useState(""); // Remaining time state
  const [toastMessage, setToastMessage] = useState("Generating Excel...");

  const [user, setUser] = useState({
    id: "",
    full_name: "",
    department: {
      code: "",
      type: "",
    },
    role: {
      id: "",
      reports: [],
      code: "",
    },
  });

  const columns = reportColumns
    .filter(
      (reportCol) =>
        reportCol.report_role_id ===
        `${reportRoles
          .filter((roleReport) => roleReport.role_id === user.role.id)
          .map((rpt) => {
            return rpt.id;
          })}`
    )
    .map((col) => {
      return { header: col.display, key: col.name };
    });

  const getProfile = async () => {
    try {
      const userId = getToken(TOKEN_COOKIES.AUTH_ID);
      await axios.get(`/api/users/${userId}`).then((data) => {
        setUser(data.data);
      });
    } catch (error) {
      toast.error("Internal server erorr !");
    }
  };

  const getReports = async (query: string) => {
    setLoading(true);

    if (totalRecord == 0) {
      const response = await getTotalReportService(query);
      if (response.success) {
        setTotalRecord(response.data);
        if (response.data == 0) {
          toast.error("No record found !");
          setLoading(false);
          return;
        }
        if (response.data >= 300000) {
          setModalMessage(true);
          setLoading(false);
          return;
        }
      } else {
        toast.error("Internal server erorr!");
      }
    } else if (totalRecord >= 300000) {
      setModalMessage(true);
      setLoading(false);
      return;
    }

    const resposne = await getAllReportService(query);
    if (resposne.success) {
      setReports(resposne.data);
    } else {
      toast.error("Internal server erorr!");
    }
    setLoading(false);
  };

  const onChangeBranch = (opt: any) => {
    setBranchs(opt);
    var value = [];
    for (var i = 0, l = opt.length; i < l; i++) {
      value.push(`'${opt[i].value}'`);
    }
    setBranch(value);
  };

  const onChangeCcy = (opt: any) => {
    setCurrency(opt);
    var value = [];
    for (var i = 0, l = opt.length; i < l; i++) {
      value.push(`'${opt[i].value}'`);
    }
    setCcy(value);
  };

  const onChangeNational = (opt: any) => {
    setNationality(opt);
    var value = [];
    for (var i = 0, l = opt.length; i < l; i++) {
      value.push(`'${opt[i].value}'`);
    }
    setNational(value);
  };

  const tableRef = useRef(null);

  const stickNavbar = () => {
    if (window !== undefined) {
      if (window.scrollY > 100) {
        setStickyClass("fixed w-full top-12 left-0 px-5 shadow border-b ");
      } else {
        setStickyClass("");
      }
    }
  };

  const saveExcel = async () => {
    setLoadingReport(true);
    let toastId: string | null = null; // Initialize with null to ensure it exists when used
    let intervalId: number | undefined = undefined;

    try {
      const timePerRecord = 0.000007;
      let estimatedTime = 0;
      let startTime = Date.now();
      let estimatedFileSizeMB = 0;
      let allRecord = 0;

      // Clear any previous interval before starting the new download process
      if (intervalId !== undefined) {
        clearInterval(intervalId);
      }

      // Fetch total record count if needed
      if (totalRecord === 0) {
        const responseTotal = await getTotalReportService(query);
        if (responseTotal.success) {
          if (responseTotal.data == 0) {
            toast.error("No record found !");
            setLoading(false);
            return;
          }
        } else {
          toast.error("Internal server erorr!");
        }
        setTotalRecord(responseTotal.data);
        allRecord = responseTotal.data;
        const totalRecords = responseTotal.data * columns?.length;
        estimatedTime = Math.ceil(totalRecords * timePerRecord);
        const estimatedFileSizeBytes = totalRecords * 7.1; // Estimate size based on records
        estimatedFileSizeMB = estimatedFileSizeBytes / (1024 * 1024);
      } else {
        allRecord = totalRecord;
        const calTotalRecords = totalRecord * columns?.length;
        const estimatedFileSizeBytes = calTotalRecords * 7.1;
        estimatedFileSizeMB = estimatedFileSizeBytes / (1024 * 1024);
        estimatedTime = Math.ceil(calTotalRecords * timePerRecord);
      }

      if (allRecord > 5000000) {
        setModalExcelMessage(true);
      }
      setShowToast(true);

      if (estimatedTime > 2280) {
        estimatedTime = 2280;
      }

      const totalEstimatedTimeInMs = estimatedTime * 1000; // Convert to milliseconds
      let progress = 99; // Set initial progress to 99% for pre-download progress

      // Start the interval to update progress and remaining time
      intervalId = window.setInterval(() => {
        const elapsedTimeInMs = Date.now() - startTime;
        const elapsedTimeInSec = Math.floor(elapsedTimeInMs / 1000);
        // const remainingTimeInSec = Math.max(
        //   estimatedTime - elapsedTimeInSec,
        //   0
        // );

        progress = Math.min(
          Math.floor((elapsedTimeInSec / estimatedTime) * 100),
          99
        );

        // Update progress and toast message
        setProgress(progress);
        setRemainingTime(`Total ${allRecord} Records`);
        setToastMessage(
          `Generating Excel... ${progress}% - ${estimatedFileSizeMB.toFixed(
            2
          )} MB`
        );

        // Switch to "Installing" message when progress reaches 99%
        if (progress === 99 || elapsedTimeInSec >= estimatedTime) {
          clearInterval(intervalId);
          setToastMessage(
            `Installing, please wait... ${progress}% - ${estimatedFileSizeMB.toFixed(
              2
            )} MB`
          );
        }
      }, 1000);

      // Prepare report request payload
      const reportRequest = {
        reportName: report?.name.toUpperCase(),
        reportDate: moment(reportDate).format("DD-MMM-YYYY"),
        branch:
          branch.length > 1
            ? branch.length < 5
              ? branch.toString().replaceAll("'", "")
              : "ALL"
            : "ALL",
        columns: columns,
        query: query,
      };

      // Make API request to generate Excel file
      const response = await axios.post(
        `${process.env.API_URL + "/reporting/to-excel"}`,
        reportRequest,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            Authorization: `Bearer ${getToken(TOKEN_COOKIES.TOKEN_NAME)}`,
          },
          responseType: "arraybuffer",
          onDownloadProgress: (progressEvent) => {
            const total = progressEvent.total ?? 1; // Total size of the response in bytes
            const current = progressEvent.loaded; // Bytes loaded so far
            const currentProgress = Math.floor((current / total) * 100); // Progress percentage

            setProgress(currentProgress);

            const elapsedTimeInMs = Date.now() - startTime;
            const remainingTimeInSec = Math.floor(
              (totalEstimatedTimeInMs - elapsedTimeInMs) / 1000
            );

            // Update the toast message with download progress
            setRemainingTime(`Total ${allRecord} Records`);
            setToastMessage(
              `Download Excel... ${currentProgress}% - ${remainingTimeInSec}s remaining`
            );

            // Final progress completion handling
            if (currentProgress === 100) {
              if (intervalId !== undefined) {
                clearInterval(intervalId); // Stop the interval when the download is complete
              }
              setProgress(100);
            }
          },
        }
      );

      // Handle file download
      const fileBlob = new Blob([response.data], {
        type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
      });

      const link = document.createElement("a");
      const url = URL.createObjectURL(fileBlob);
      link.href = url;
      link.download = `${reportRequest.reportName}.xlsx`;
      link.click();
      URL.revokeObjectURL(url);
    } catch (e) {
      if (toastId) {
        toast.error("Failed to generate Excel report. Please try again.", {
          id: toastId,
        });
      }
    } finally {
      // Reset progress and other states before starting a new download
      setProgress(0);
      setRemainingTime("");
      setToastMessage("Starting Excel report generation...");
      setLoadingReport(false);
      setShowToast(false);
    }
  };

  const getMetaData = async () => {
    try {
      await axios.get(`/api/metadatas/`).then((data) => {
        setMetadata(data.data);
      });
    } catch (error) {
      toast.error("Internal server erorr !");
    }
  };

  useEffect(() => {
    getProfile();
    getMetaData();
  }, []);

  useEffect(() => {
    let paramCol = reportParams.filter((pmcl) => pmcl.type === "date");
    setParamSize(paramCol.length);

    var str = report?.code;

    var query;
    if (paramSize === 2) {
      query = str
        ?.replaceAll(
          "param_from_date",
          `'${moment(fromDate).format("DD-MMM-YYYY")}'`
        )
        .replaceAll(
          "param_to_date",
          `'${moment(toDate).format("DD-MMM-YYYY")}'`
        )
        .replace("param_branch", `${branch}`)
        .replace("param_ccy", `${ccy}`)
        .replaceAll("param_national", `${national}`)
        .replace("param_trx_type  ", `'${trxType}'`)
        .replace("param_acct_no", `${acctNo}`);
    } else {
      query = str
        ?.replaceAll(
          "param_report_date",
          `'${moment(reportDate).format("DD-MMM-YYYY")}'`
        )
        .replace("param_branch", `${branch}`)
        .replace("param_ccy", `${ccy}`)
        .replaceAll("param_national", `${national}`)
        .replace("param_trx_type  ", `'${trxType}'`)
        .replace("param_acct_no", `${acctNo}`);
    }

    setTotalRecord(0);
    setQuery(`${query}`);

    window.addEventListener("scroll", stickNavbar);

    return () => {
      window.removeEventListener("scroll", stickNavbar);
    };
  }, [
    show,
    branch,
    ccy,
    reportDate,
    fromDate,
    toDate,
    national,
    acctNo,
    trxType,
    reportParams,
    report?.code,
    paramSize,
  ]);

  const handlePageChange = (page: number) => {
    window.scrollTo({
      top: 0,
      behavior: "smooth", // Smooth scroll
    });
    setCurrentPage(page);
  };

  const totalPages = Math.ceil((reports?.length || 0) / show);

  const getCurrentPageData = () => {
    const startIndex = (currentPage - 1) * show;
    const endIndex = startIndex + show;
    const paginatedData = reports?.slice(startIndex, endIndex) || [];
    return Array.isArray(paginatedData) ? paginatedData : [];
  };

  return (
    <div>
      <div className="flex justify-between items-center">
        <h1 className="text-sm font-semibold text-gray-700">{report?.name}</h1>
        <div className="flex items-center py-4 overflow-x-auto whitespace-nowrap">
          <Link href="/" className="text-gray-600 hover:text-blue-500">
            <Home className="w-4 h-4" />
          </Link>

          <span className="mx-3 text-gray-500 hover:text-blue-500 rtl:-scale-x-100">
            <ChevronRight className="w-4 h-4" />
          </span>
          <div className="flex items-center text-blue-600 -px-2 ">
            <BarChart3 className="w-4 h-4 mx-2" />
            <span className="mx-2 text-xs">{report?.name}</span>
          </div>
          <span className="mx-3 text-blue-500 rtl:-scale-x-100">
            <ChevronRight className="w-4 h-4" />
          </span>
        </div>
      </div>

      <div className="bg-white px-4 pb-4 rounded-lg mt-4 pt-1">
        <div
          className={cn(
            "transaction ease-linear duration-300 bg-white",
            stickyClass
          )}
        >
          <div className="flex justify-between py-3 overflow-auto">
            <div className="flex items-center">
              <div className="border-2 rounded-md px-1 relative">
                <label className="text-[9px] font-medium absolute -top-3 bg-white p-1 px-2 uppercase">
                  Show
                </label>
                <select
                  className={cn(
                    "text-[11px] px-1 bg-transparent pl-2 focus:outline-none mt-2 mb-1",
                    showOpt ? "hidden" : ""
                  )}
                  value={show}
                  onChange={(e) => setShow(parseInt(e.target.value))}
                >
                  {[
                    { name: "10", val: 10 },
                    { name: "20", val: 20 },
                    { name: "50", val: 50 },
                    { name: "100", val: 100 },
                  ].map((pageSize, index) => (
                    <option key={index} value={pageSize.val}>
                      {pageSize.name}
                    </option>
                  ))}
                </select>
                <input
                  type="number"
                  value={show}
                  onChange={(e) => setShow(parseInt(e.target.value))}
                  className={cn(
                    "w-[50px] text-xs focus:border-none focus:outline-none pl-2 mt-2 mb-1",
                    !showOpt ? "hidden" : ""
                  )}
                />
              </div>
              <Button
                size="icon"
                variant="ghost"
                className="w-8 h-8 ml-1 text-gray-500 bg-gray-200"
                onClick={() => setShowOpt(!showOpt)}
              >
                {showOpt ? (
                  <X className="w-4 h-4" />
                ) : (
                  <ArrowLeftRight className="w-4 h-4" />
                )}
              </Button>
            </div>

            <div className="px-3 flex">
              {`${reportColumns.filter(
                (reportCol) =>
                  reportCol.report_role_id ===
                    `${reportRoles
                      .filter(
                        (roleReport) => roleReport.role_id === user.role.id
                      )
                      .map((rpt) => {
                        return rpt.id;
                      })}` && reportCol.name === "LOAN_CO"
              )}` ? (
                <div className="px-2 border rounded-md flex items-center mr-2">
                  <span className="pr-2 text-gray-500">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      strokeWidth={1.5}
                      stroke="currentColor"
                      className="w-4 h-4"
                    >
                      <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
                      />
                    </svg>
                  </span>
                  <input
                    type="text"
                    className="focus:outline-none text-xs py-1 mt-1 w-[150px]"
                    onChange={(e) => setSearch(e.target.value)}
                    placeholder="Search co id..."
                  />
                </div>
              ) : (
                ""
              )}
              {reportParams.map((param) =>
                param.type === "date" ? (
                  paramSize === 2 ? (
                    <Popover key={param.id}>
                      <PopoverTrigger asChild>
                        <Button
                          variant={"outline"}
                          className={cn(
                            "w-[180px] h-8 px-2 justify-start text-left font-normal shadow-none hover:bg-white mx-1",
                            !(param.name === "param_from_date"
                              ? fromDate
                              : toDate) && "text-muted-foreground"
                          )}
                        >
                          <CalendarIcon className="mr-2 h-4 w-4" />
                          {(
                            param.name === "param_from_date" ? fromDate : toDate
                          ) ? (
                            moment(
                              param.name === "param_from_date"
                                ? fromDate
                                : toDate
                            ).format("DD-MMM-YYYY")
                          ) : (
                            <span className="text-xs text-gray-400">
                              {param.display}
                            </span>
                          )}
                        </Button>
                      </PopoverTrigger>
                      <PopoverContent className="w-auto p-0">
                        <Calendar
                          mode="single"
                          selected={
                            param.name === "param_from_date" ? fromDate : toDate
                          }
                          onSelect={
                            param.name === "param_from_date"
                              ? setFromDate
                              : setToDate
                          }
                          initialFocus
                        />
                      </PopoverContent>
                    </Popover>
                  ) : (
                    <Popover key={param.id}>
                      <PopoverTrigger asChild>
                        <Button
                          variant={"outline"}
                          className={cn(
                            "w-[180px] h-8 px-2 justify-start text-left font-normal shadow-none hover:bg-white mx-1",
                            !reportDate && "text-muted-foreground"
                          )}
                        >
                          <CalendarIcon className="mr-2 h-4 w-4" />
                          {reportDate ? (
                            format(reportDate, "dd-MMM-yyyy")
                          ) : (
                            <span className="text-xs text-gray-400">
                              {param.display}
                            </span>
                          )}
                        </Button>
                      </PopoverTrigger>
                      <PopoverContent className="w-auto p-0">
                        <Calendar
                          mode="single"
                          selected={reportDate}
                          onSelect={setReportDate}
                          initialFocus
                        />
                      </PopoverContent>
                    </Popover>
                  )
                ) : param.type === "select" ? (
                  <SnSelect
                    key={param.id}
                    options={
                      param.name === "param_branch"
                        ? user.department.type === "Branch"
                          ? metaBranchs.filter(
                              (f) => f.value === user.department.code
                            )
                          : metaBranchs
                        : param.name === "param_ccy"
                        ? metaCurrencies
                        : param.name === "param_national"
                        ? metaNationlities
                        : []
                    }
                    value={
                      param.name === "param_branch"
                        ? branchs
                        : param.name === "param_national"
                        ? nationality
                        : currency
                    }
                    onChange={(opt) =>
                      param.name === "param_branch"
                        ? onChangeBranch(opt)
                        : param.name === "param_national"
                        ? onChangeNational(opt)
                        : onChangeCcy(opt)
                    }
                    placholder={`Select ${param.display} ...`}
                  />
                ) : param.type === "text" ? (
                  <div className="px-2 border rounded-md flex items-center mx-2">
                    <span className="pr-2 text-gray-500">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 24 24"
                        strokeWidth={1.5}
                        stroke="currentColor"
                        className="w-4 h-4"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
                        />
                      </svg>
                    </span>
                    <input
                      type="text"
                      className="focus:outline-none text-xs py-1 mt-1 w-[150px]"
                      onChange={(e) =>
                        param.name === "param_acct_no"
                          ? setAcctNo(e.target.value)
                          : setTrxType(e.target.value)
                      }
                      placeholder={param.display}
                    />
                  </div>
                ) : (
                  ""
                )
              )}

              <Button
                className="ml-3 h-8 text-[10px] px-3"
                onClick={() => getReports(query)}
                disabled={loading}
              >
                {!loading ? (
                  <div className="flex items-center">
                    <span>View</span>
                    <ArrowRight className="w-3 h-3 ml-1" />
                  </div>
                ) : (
                  <Loader2 className="animate-spin " />
                )}
              </Button>
              <div className="px-2"></div>
              <Button
                size="icon"
                variant="outline"
                disabled={loadingReport}
                className=" w-8 h-8 bg-blue-500 text-white hover:bg-blue-600 hover:text-white"
                onClick={saveExcel}
              >
                {loadingReport ? (
                  <Loader2 className="animate-spin " />
                ) : (
                  <Download className="w-4 h-4" />
                )}
              </Button>
            </div>
          </div>
        </div>
        <div className="overflow-x-auto w-full mt-3">
          <Table
            ref={tableRef}
            className="mx-auto w-full whitespace-nowrap overflow-hidden mb-5"
          >
            <TableHeader className="bg-gray-100 uppercase">
              <TableRow className="text-[10px] font-semibold text-gray-600 py-1">
                <TableHead className="py-0 h-8 px-4">#</TableHead>
                {reportColumns
                  .filter(
                    (reportCol) =>
                      reportCol.report_role_id ===
                      `${reportRoles
                        .filter(
                          (roleReport) => roleReport.role_id === user.role.id
                        )
                        .map((rpt) => {
                          return rpt.id;
                        })}`
                  )
                  .map((col) => {
                    return (
                      <TableHead key={col.id} className="py-0 h-8 px-4">
                        {col.display}
                      </TableHead>
                    );
                  })}
              </TableRow>
            </TableHeader>
            <TableBody>
              {getCurrentPageData().map((report, index) => {
                const no = (currentPage - 1) * show + (index + 1);
                return (
                  <TableRow
                    key={index}
                    className="even:bg-blue-50 odd:bg-white "
                  >
                    <TableCell className="text-[10px] text-gray-600 py-1 px-4">
                      {no}
                    </TableCell>
                    {reportColumns
                      .filter(
                        (reportCol) =>
                          reportCol.report_role_id ===
                          `${reportRoles
                            .filter(
                              (roleReport) =>
                                roleReport.role_id === user.role.id
                            )
                            .map((rpt) => {
                              return rpt.id;
                            })}`
                      )
                      .map((col, index) => (
                        <TableCell
                          className="text-[10px] text-gray-600 py-1 px-4"
                          key={index}
                        >
                          {col.type == "date"
                            ? moment(report[col.name]).format("DD MMM YYYY")
                            : report[col.name]}
                        </TableCell>
                      ))}
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </div>
      </div>

      {reports?.length > 10 && (
        <div className="flex justify-end mt-8">
          <Pagination
            currentPage={currentPage}
            totalPages={totalPages}
            onPageChange={handlePageChange}
          />
        </div>
      )}

      <ErrorDialog
        totalRecard={totalRecord}
        isOpen={modalMessage}
        onClose={() => setModalMessage(false)}
      />

      <Toast
        message={toastMessage}
        showToast={showToast}
        onClose={() => setShowToast(false)}
        progress={progress}
        remaining={remainingTime}
      />

      <ErrorDialogReport
        totalRecard={totalRecord}
        isOpen={modalExcelMessage}
        onClose={() => setModalExcelMessage(false)}
      />
    </div>
  );
};

export default ReportClient;
