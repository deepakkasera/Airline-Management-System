package com.example.airlinemanagementstarter.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

public abstract class BaseModel {
    private int id;
}
