package com.taskmanager.controllers.dto.task;

import com.taskmanager.controllers.dto.ResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "Транспорт ответа обновления задачи")
public class TaskUpdateResponseDto extends ResponseDto {

    @Schema(description = "Идентификатор задачи")
    private UUID taskId;

    @Schema(description = "Номер задачи")
    private Long taskNumber;
}
