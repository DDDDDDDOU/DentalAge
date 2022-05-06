
export default class UserInstance {
    constructor () {
        this.userName = 'doujiawei'
    }



    static getInstance () {
        if (!UserInstance.instance) {
            UserInstance.instance = new UserInstance();
        }
        return UserInstance.instance;
    }

    getUserName () {
        return this.userName
    }


}

