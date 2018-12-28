export class User {
    constructor(
        public id: number,
        public username: string,
        public email: string,
        public password: string,
        public token: string,
        public firstname: string,
        public lastname: string
    ) {}
}

