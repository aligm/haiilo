package com.haiilo.checkout.product.model;

import com.haiilo.checkout.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.web.servlet.HandlerMapping;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;


/**
 * Validate that the sku value isn't taken yet.
 */
@Target({FIELD, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProductSkuUnique.ProductSkuUniqueValidator.class)
public @interface ProductSkuUnique {

    String message() default "{Exists.product.sku}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ProductSkuUniqueValidator implements ConstraintValidator<ProductSkuUnique, String> {

        private final ProductService productService;
        private final HttpServletRequest request;

        public ProductSkuUniqueValidator(final ProductService productService, final HttpServletRequest request) {
            this.productService = productService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                ((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentSku = pathVariables.get("sku");
            if (currentSku != null && value.equalsIgnoreCase(productService.get(currentSku).getSku())) {
                // value hasn't changed
                return true;
            }
            return !productService.skuExists(value);
        }

    }

}
