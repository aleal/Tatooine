package com.ktech.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ktech.repositories.GenericRepository;

@Constraint(validatedBy = UniqueValueValidator.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueValue {
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<? extends GenericRepository<?>> repository();

	String field();
}
