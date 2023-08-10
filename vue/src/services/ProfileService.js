import axios from 'axios';

const http = axios.create({
    baseURL: "http://127.0.0.1:9000"

});

export default {
    createProfile(formData){
        return http.post(`/create-profile`, formData);
    },

    getHome(){
        return http.get(`/`)
        //check to see why this isn't connecting after user hits create profile
    },

    updateProfile(profileId, newForm){
        return http.post(`/profile/${profileId}`, newForm);
    },


}