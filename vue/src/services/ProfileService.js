import axios from 'axios';

const api = {
    createProfile(formData) {
        // alert('Create Profile...');
        // alert(formData.userId);
        return axios.post('/createProfile', formData);
    },

    getHome() 
    {
        return axios.get('/');
    },

    updateProfile(userId, formData) 
    {
        // alert('Updating Profile...');
        // alert(formData.displayName);
        // alert(formData.userId);
        
        return axios.post(`/profile/${userId}`, formData);
    },

    getProfileInfo(userId) 
    {   
        return axios.get(`/profile/${userId}`);
    },
    getPicUrl(picId) 
    {   
        return axios.get(`profilePic/${picId}`)
            .then(response => 
            {
                const url = response.data.url; 
                // alert(url + ' is being returned from the getPicURL method');
                return url;
            })
            .catch(error => 
            {
                console.error('Error fetching profile picture URL:', error);
                return ''; 
            });
    },
    
};

export default api;


