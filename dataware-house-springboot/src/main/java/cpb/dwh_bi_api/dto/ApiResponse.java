package cpb.dwh_bi_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
	private int code;
	private String message;
	private boolean success;
	private T data;
	private Object errors;

	public static <T> ApiResponse<T> success(T data) {
		return ApiResponse.<T>builder()
			.code(200)
			.message("Success")
			.success(true)
			.data(data)
			.build();
	}

	public static <T> ApiResponse<T> success(String message, T data) {
		return ApiResponse.<T>builder()
			.code(200)
			.message(message)
			.success(true)
			.data(data)
			.build();
	}

	public static <T> ApiResponse<T> error(int code, String message) {
		return ApiResponse.<T>builder()
			.code(code)
			.message(message)
			.success(false)
			.build();
	}

	public static <T> ApiResponse<T> error(int code, String message, Object errors) {
		return ApiResponse.<T>builder()
			.code(code)
			.message(message)
			.success(false)
			.errors(errors)
			.build();
	}
}
