package com.davizin.anticheat.models.annotations;

import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CheckHandler {


    String name();

    CheckType check() default CheckType.BETA;

    int maxViolations() default 15;

    String description() default "Esta infração ainda não tem descrição";

    enum CheckType {
        BETA("B"), // No bans will be executed for checks with this type
        SNAPSHOT("S"), // Will only flag if the threshold reaches the double it was supposed to
        RELEASE("R"); // Default Check Type

        @Getter
        private String name;

        CheckType(String name) {
            this.name = name;
        }

        public char getChar() {
            return name().charAt(0);
        }

    }
}
