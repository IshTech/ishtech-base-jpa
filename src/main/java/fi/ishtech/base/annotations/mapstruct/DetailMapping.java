package fi.ishtech.base.annotations.mapstruct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.Qualifier;

/**
 * Qualifier for mapstruct mappers.<br>
 * Used on entities that need nested entities,<br>
 * may include deeper levels
 *
 * @author Muneer Ahmed Syed
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface DetailMapping {

}