package com.stewartlavenia.tally.errorHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
// my last test was still expected 400, but was 404 instead of 500 as in vid this is suppose to give me a grnBar
public class GlobalErrorHandler {
	private enum LogStatus {
		STACK_TRACE, MESSAGE_ONLY
	}
	
	/**
	 * 
	 * @param e
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest webRequest) {
		
		return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY);
	}
	
	/**
	 * 
	 * @param e
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)

	public Map<String, Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest webRequest) {
		return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
//		return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY);
		
	}
	/* ---------------- dup ---------------
	private Map<String, Object> createExceptionMessage(ConstraintViolationException e, HttpStatus notFound, WebRequest webRequest, LogStatus logStatus) {

		return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, logStatus);
	
//		private Map<String, Object> createExceptionMessage(ConstraintViolationException e, HttpStatus badRequest, WebRequest webRequest, LogStatus logStatus) {
//		return null;
	}
	
	*/ 

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	/**
	 * 
	 * @param e
	 * @param webRequest
	 * @return
	 */
	public Map<String, Object> handleNoSuchElementException(NoSuchElementException e, WebRequest webRequest) {
		return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
		
	}
	
	// To handle unknown generic exceptions
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	
	/**
	 * 
	 * @param e
	 * @param webRequest
	 * @return
	 */
	public Map<String, Object> handleException(Exception e, WebRequest webRequest) {
		return createExceptionMessage(e, HttpStatus.INTERNAL_SERVER_ERROR,  webRequest, LogStatus.STACK_TRACE);
	}
	
	/* ---------------- dup --------------- causes err on all my return CEMess goes red why? 
	private Map<String, Object> createExceptionMessage(Exception e, HttpStatus internalServerError,
			WebRequest webRequest, LogStatus logStatus) {
		return createExceptionMessage(e, HttpStatus.INTERNAL_SERVER_ERROR,  webRequest, LogStatus.STACK_TRACE);
//		return null;
	}
	*/
	/**
	 * 
	 * @param e
	 * @param status
	 * @param webRequest
	 * @param logStatus 
	 * @return
	 */
	private Map<String, Object> createExceptionMessage(Exception e, HttpStatus status, WebRequest webRequest, LogStatus logStatus) {

		Map<String, Object> error = new HashMap();
		String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		
		if(webRequest instanceof ServletWebRequest) {
			error.put("uri", ((ServletWebRequest)webRequest).getRequest().getRequestURI());
		}
		
		error.put("message", e.toString());
		error.put("Status code", status.value());
		error.put("timestamp", timestamp);
		error.put("reason", status.getReasonPhrase());
		
		if(logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", e.toString());
		} else {
			// logs the entire stack trace if it's a generic error
			log.error("Exception:", e);
		}
		
		return error;
	}
}
