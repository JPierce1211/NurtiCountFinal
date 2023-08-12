import axios from 'axios';

const api = {
    createProfile(formData) {
        alert('Creating Profile...');
        alert('User ID # retrieved from Create method' + formData.userId);
        return axios.post('/createProfile', formData);
    },

    getHome() 
    {
        return axios.get('/');
    },

    updateProfile(userId, formData) 
    {
        alert('Updating Profile...');
        alert('Display Name from Update Method: ' + formData.displayName);
        alert('User ID # from UpdateProfile() method: ' + userId);
        return axios.post(`/profile/${userId}`, formData);
    },

    getProfileInfo(userId) 
    {   
        alert('User ID # retrieved from getProfileInfo() : ' + userId);

        const url = `/profile/${userId}`;
        alert('URL output: ' + url);
        return axios.get(url);

        // return axios.get(`/profile/${userId}`);
    },

    getFoodByName(searchName)
    {
        
    }
};

export default api;