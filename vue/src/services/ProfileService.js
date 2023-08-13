import axios from 'axios';

const api = {
    createProfile(formData) {
        alert('Create Profile...');
        alert(formData.userId);
        return axios.post('/createProfile', formData);
    },

    getHome() 
    {
        return axios.get('/');
    },

    updateProfile(userId, formData) 
    {
        alert('Updating Profile...');
        alert(formData.displayName);
        alert(formData.userId);
        return axios.post(`/profile/${userId}`, formData);
    },

    getProfileInfo(userId) 
    {   

        alert(userId + ' is being passed into the getProfileInfo() as a parameter'); // delete later
        return axios.get(`/profile/${userId}`);
    },
    getPicUrl(picId)
    {   

        

        alert(picId + ' is being passed thru the getPicURL method as a parameter'); // delete later
        return axios.get(`profilePic/${picId}`);
    }
};

export default api;


