package org.zucc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@TableName("question_choice")
public class Issue implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField(value = "question")
    private String question;
    @TableField(value = "answer_A")
    private String answer_A;
    @TableField(value = "answer_B")
    private String answer_B;
    @TableField(value = "answer_C")
    private String answer_C;
    @TableField(value = "answer_D")
    private String answer_D;

    @TableField(value = "right_answer")
    private String right_answer;

    @TableField(value = "analysis")
    private String analysis;

    @TableField(value = "score")
    private int score;

    @TableField(value = "classify")
    private String classify;
}
