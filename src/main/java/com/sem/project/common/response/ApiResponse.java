package com.sem.project.common.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
//Response Example
//    {
//        "success": true,
//            "message": "Employee created successfully",
//            "data": {
//        "id": 1,
//                "employeeCode": "EMP001"
//    }
// }
}
