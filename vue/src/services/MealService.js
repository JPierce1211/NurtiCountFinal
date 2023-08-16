import axios from 'axios';

const api = 
{
    getMeals()
        {
            return axios.get(`/meals`);
        },
        
    deleteMeal(mealId){
        return axios.delete(`/meals/${mealId}`);
    }    
};

export default api;