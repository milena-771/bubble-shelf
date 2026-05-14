
import { AnswerModel } from "./answer-model";

export interface QuestionModel {
    answers: AnswerModel[];
    label: string;
    order: number;
}
