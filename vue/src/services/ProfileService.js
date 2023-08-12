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
        alert(userId);

        const url = `/profile/${userId}`;
        alert(url);
        return axios.get(url);

        // return axios.get(`/profile/${userId}`);
    },

    getFoodByName(searchName)
    {
       const url = `/foodByName/${searchName}`;
       return axios.get(url)
       .then(response =>{
           return response.data;
       })
       .catch(error =>{
           console.error(error);
           throw error;
       });
    },
};

export default api;


