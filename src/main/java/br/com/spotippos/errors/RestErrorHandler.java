package br.com.spotippos.errors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class RestErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected ErrorWrapper handleArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
		ErrorWrapper error = new ErrorWrapper("Erros durante persistência de nova propriedade em Spotippos");
		ex.getBindingResult().getFieldErrors().forEach(f -> {
			error.addErrors(f.getDefaultMessage());
		});
		return error;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	protected ErrorWrapper handleUriNotFound(NoHandlerFoundException ex, HttpServletRequest request){
		ErrorWrapper error = new ErrorWrapper("Erro ao acessar o recurso informado");
		error.addErrors("A URI " + request.getRequestURL() + " não foi localizada no webservice.");
		return error;
	}
	
	@ExceptionHandler(DuplicatePropertyException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ResponseBody
	protected ErrorWrapper handleDuplicatePropertyException(DuplicatePropertyException ex){
		ErrorWrapper error = new ErrorWrapper("Erro ao salvar novo imóvel em Spotippos");
		error.addErrors(ex.getMessage());
		return error;
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected ErrorWrapper handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
		ErrorWrapper error = new ErrorWrapper("Erro ao salvar novo imóvel em Spotippos");
		error.addErrors("Por favor, verifique se os dados passados via JSON não estão com falhas, "
				+ "como texto em informação numérica ou valores numéricos altos para valores de X, Y e metros quadrados");
		return error;
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	protected ErrorWrapper handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest req){
		ErrorWrapper error = new ErrorWrapper("Erro ao salvar novo imóvel em Spotippos");
		error.addErrors("A URL " + req.getRequestURL() + " não permite o método " + req.getMethod() + ". Por favor, verifique se está invocando a URL correta. ");
		return error;
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected ErrorWrapper handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest req){
		ErrorWrapper error = new ErrorWrapper("Erro ao pesquisar imóveis em Spotippos");
		error.addErrors("O método " + req.getMethod() + " para a URL " + req.getRequestURL() + " exige que os valores de ax, ay, bx e by sejam informados.");
		return error;
	}
}
