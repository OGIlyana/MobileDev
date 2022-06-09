package ru.mirea.ochirgoryaeva.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name1;
    public int salary;
}