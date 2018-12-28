import { SafeStyle } from '@angular/platform-browser';

export interface MovieAPI {
    id: number;
    title: string;
    poster_path: string;
    backdrop_path: SafeStyle;
    overview: string;
    release_date: string;
    adult: boolean;
    budget: number;
    homepage: string;
    imdb_id: string;
    runtime: number;
    tagline: string;
}
