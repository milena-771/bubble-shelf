
import { AnswerModel } from "./answer-model";

export interface QuestionModel {
    Answers: AnswerModel[];
    label: string;
    order: number;
}
