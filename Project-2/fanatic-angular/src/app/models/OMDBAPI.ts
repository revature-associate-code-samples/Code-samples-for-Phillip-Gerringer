// http://www.omdbapi.com/
import { RatingsAPI } from 'src/app/models/ratingsAPI';

export interface OMDBAPI {
    id: number;
    Title: string;
    Genre: string;
    Ratings: RatingsAPI[];
    Rated: string;
    Metascore: string;
    imdbRating: string;
    RottenTomatoes: string;
    MovieFanatic: number;
}
