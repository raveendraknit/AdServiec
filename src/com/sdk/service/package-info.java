@org.hibernate.annotations.TypeDef(
        name = "pg-uuid",
        defaultForType = UUID.class,
        typeClass = PostgresUUIDType.class
)
package com.sdk.service;

import java.util.UUID;

import org.hibernate.type.PostgresUUIDType;