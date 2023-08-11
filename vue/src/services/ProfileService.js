import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://127.0.0.1:9000"

//});

export default {
    createProfile(formData){
        alert(formData);
        return axios.post(`/createProfile`, formData);
    },

    getHome(){
        return axios.get(`/`)
        //check to see why this isn't connecting after user hits create profile
    },

    updateProfile(profileId, newForm){
        return axios.post(`/profile/${profileId}`, newForm);
    },

    const getProfileInfo =() => {
            return axios.get('')
        }

}