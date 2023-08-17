import axios from 'axios';

const api = 
{
    getMeals()
        {
            return axios.get(`/meals`);
        },
        
    deleteMeal(mealId){
        return axios.delete(`/meals/${mealId}`);
    }, 
    
    getFoodMeals(mealId){
        return axios.get(`meals/food/mealFood/${mealId}`)
    }
};

export default api;