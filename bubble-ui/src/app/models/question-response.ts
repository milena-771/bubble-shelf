import { ProgressModel } from "./progress-model";
import { QuestionModel } from "./question-model";

export interface QuestionResponse {
    progress: ProgressModel;
    question: QuestionModel;
    selectedAnswer: number;
    language: string;
    sessionId: string;
}
