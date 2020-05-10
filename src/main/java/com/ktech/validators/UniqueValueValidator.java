package com.ktech.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ktech.repositories.GenericRepository;
import com.ktech.utils.GenericUtils;

/**
 * Validates if value is unique at DB. (Tip: Apply index on the tested field)
 * @author aleal
 *
 */
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
	private GenericRepository<?> repository;
	private String field;
	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.repository = GenericUtils.getInstanceOf(constraintAnnotation.repository());
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		Object entity = this.repository.findOneByField(field, value);
		return Objects.isNull(entity);
	}
	
}