package local.heftyb.howto.services;

import local.heftyb.howto.models.ValidationError;

import java.util.List;

public interface HelperFunctions
{
    List<ValidationError> getConstraintViolation(Throwable cause);
}
