package security.example.security.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

@Controller
public class CustomErrorController implements ErrorController {
    @Autowired
    private ErrorAttributes errorAttributes;            

    //SobreEscribir la ruta /error de SpringBoot
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Exception exception, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        Map<String, Object> errorMap = errorAttributes.getErrorAttributes(new ServletWebRequest(request),
                ErrorAttributeOptions.of(Arrays.asList(
                ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.STACK_TRACE)));

        model.addAttribute("timestamp", new Date());
        model.addAttribute("path", errorMap.get("path"));
        model.addAttribute("error", errorMap.get("error"));
        model.addAttribute("status", errorMap.get("status"));
        model.addAttribute("message", errorMap.get("message"));
        model.addAttribute("exception", errorMap.get("exception"));
        model.addAttribute("trace", errorMap.get("trace"));

        Integer code = Integer.valueOf(status.toString());

        return switch (code) {
            case 403 -> "error/error-403"; //HttpStatus.FORBIDDEN
            case 404 -> "error/error-404"; //HttpStatus.NOT_FOUND
            case 405 -> "error/error-405"; //HttpStatus.METHOD_NOT_ALLOWED
            case 500 -> "error/error-500"; //HttpStatus.INTERNAL_SERVER_ERROR
            default -> "error/error";
        };
    }
}
