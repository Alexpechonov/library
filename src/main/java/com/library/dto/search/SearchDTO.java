package com.library.dto.search;

import com.library.dto.instruction.InstructionDTO;

/**
 * Created by user on 04.08.2017.
 */
public class SearchDTO {
    private InstructionDTO instruction;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public InstructionDTO getInstruction() {
        return instruction;
    }

    public void setInstruction(InstructionDTO instruction) {
        this.instruction = instruction;
    }
}
