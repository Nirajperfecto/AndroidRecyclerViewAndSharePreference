package com.example.todonotesapp.clickListners

import com.example.todonotesapp.model.Notes

interface ItemClickListner {
    fun onClick(notes: Notes)
}