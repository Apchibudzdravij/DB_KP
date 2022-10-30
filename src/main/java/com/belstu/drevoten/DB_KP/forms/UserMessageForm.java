package com.belstu.drevoten.DB_KP.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessageForm {
    private String Sender;
    private String Receiver;
    private String Content;
    private String DateAndTime;
}
