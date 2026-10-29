import { ProfilModel } from "./profil-model";   
import { ComicModel } from "./comic-model";

export interface ResultModel {
    sessionId: string;
    language: string;
    statistics: Map<number, number>;
    profileDescription: ProfilModel[];
    recommendedComics: ComicModel[];
}