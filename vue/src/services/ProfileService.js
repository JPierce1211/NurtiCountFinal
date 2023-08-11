import axios from 'axios';

const api = {
    createProfile(formData) {
        alert(formData.displayName);
        alert(formData.userId);
        return axios.post('/createProfile', formData);
    },

    getHome() 
    {
        return axios.get('/');
    },

    updateProfile(userId, formData) 
    {
        alert(formData.userId);
        return axios.post(`/profile/${userId}`, formData);
    },

    getProfileInfo(userId) 
    {
        return axios.get(`/profile/${userId}`);
    },
};

export default api;