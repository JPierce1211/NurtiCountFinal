import axios from 'axios';

const http = axios.create({
    baseURL: "http://127.0.0.1:9000"

});

export default {
    createProfile(userId, formData){
        return http.post(`/create-profile/${userId}`, formData);
    },

    getHome(){
        return http.get(`/`)
    },

}