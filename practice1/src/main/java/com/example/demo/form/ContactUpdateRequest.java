package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ContactUpdateRequest extends ContactRequest implements Serializable{
	  @NotNull
	  private Long id;
}
