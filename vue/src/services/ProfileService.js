import axios from 'axios';

const http = axios.create({
    baseURL: "http://127.0.0.1:9000"

});

export default {
    createProfile(userId, formData){
        return http.post(`/profile/${userId}`, formData);
    },

    getHome(){
        return http.get(`/`)
        //check to see why this isn't connecting after user hits create profile
    },

    updateProfile(profileId, newForm){
        return http.post(`/profile/${profileId}`, newForm);
    },


}