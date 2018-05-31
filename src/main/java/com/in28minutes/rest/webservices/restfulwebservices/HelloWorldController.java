/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.helloworld.HelloWorldBean;

/**
 * 
 * @author <a href=mailto:support@aeronomos.org>aeronomos</a>
 *
 *         $Id: $
 * 
 */

// Controller
@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello Cruel World!";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello Bean");
	}

	// hello-world/path-variable/in28minutes
	//
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello Bean, %s", name));
	}

	// hello-world/path-variable/in28minutes
	//
	// public String helloWorldI18N(
	// @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
	// return messageSource.getMessage("good.morning.message", null, locale);
	// }
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldI18N() {
		return messageSource.getMessage("good.morning.message", null,
				LocaleContextHolder.getLocale());
	}
}
