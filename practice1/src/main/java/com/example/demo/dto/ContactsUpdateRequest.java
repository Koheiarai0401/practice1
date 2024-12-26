package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ContactsUpdateRequest extends ContactsRequest implements Serializable{
	  @NotNull
	  private Long id;
}
