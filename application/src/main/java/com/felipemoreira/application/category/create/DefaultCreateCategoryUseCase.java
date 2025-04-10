package com.felipemoreira.application.category.create;

import com.felipemoreira.domain.category.entity.Category;
import com.felipemoreira.domain.category.interfaces.CategoryGateway;
import com.felipemoreira.domain.validation.handler.Notification;
import com.felipemoreira.domain.validation.handler.ThrowsValidationHandler;
import io.vavr.control.Either;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand command) {
        final var category = Category.newCategory(command.name(), command.description(), command.isActive());

        final var notification = Notification.create();
        category.validate(notification);

        if (notification.hasError()) {
            //
        }

        return CreateCategoryOutput.from(this.categoryGateway.create(category));
    }
}
