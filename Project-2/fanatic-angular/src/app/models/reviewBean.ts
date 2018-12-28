export class ReviewBean {
    constructor (
        public review_id: number,
        public user_id: number,
        public movie_id: number,
        public review: string,
        public rating: number,
        public approval_id: number,
        public review_date: Date,
        public review_expire_date: Date
    ) {}
}
