export default class URLInstance {
    constructor () {
        this.DentalPredictServerURL = "http://1.13.19.206:5000/"
        this.BackendServerURL = "http://localhost:8081/"
        this.FrontendURL = "http://localhost:3000/"

        // this.BackendServerURL = "http://dw7x6m.natappfree.cc"
        // this.FrontendURL = "http://47.101.32.149/"

    }



    static getInstance () {
        if (!URLInstance.instance) {
            URLInstance.instance = new URLInstance();
        }
        return URLInstance.instance;
    }

    getDentalPredictServerURL () {
        return this.DentalPredictServerURL
    }

    getBackendServerURL () {
        return this.BackendServerURL
    }

    getFrontendURL () {
        return this.FrontendURL
    }

    constructFrontendURL (suffix) {
        return `${this.FrontendURL}${suffix}`
    }


}

