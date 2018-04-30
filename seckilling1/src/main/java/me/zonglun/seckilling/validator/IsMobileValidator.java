package me.zonglun.seckilling.validator;
import  javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import me.zonglun.seckilling.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

public class IsMobileValidator implements ConstraintValidator<IsMoblie, String> {

	private boolean required = false;
	
	@Override
	public void initialize(IsMoblie constraintAnnotation) {
		required = constraintAnnotation.required();
	}


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {
			return ValidatorUtil.isMobile(value);
		}else {
			if(StringUtils.isEmpty(value)) {
				return true;
			}else {
				return ValidatorUtil.isMobile(value);
			}
		}
	}

}
