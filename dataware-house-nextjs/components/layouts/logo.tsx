const Logo =()=>{

    return(
        <div className="flex flex-row items-center">
          <div className="mr-2 bg-blue-700 rounded flex items-center justify-center w-8 h-8">
            <span className="font-semibold text-white text-xs text-center">
              CPB
            </span>
          </div>
          <div className="flex flex-col">
            <span className=" uppercase text-[10px] font-medium text-gray-600">
              Datawarehouse
            </span>
            <span className="font-bold uppercase text-xs text-gray-700">
              Cpbank.Bi
            </span>
          </div>
        </div>
        
    );
}
export default Logo;