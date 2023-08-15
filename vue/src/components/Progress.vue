<template>
    <div>
        <div class="center-content">
            <h3>
                Weight Loss Progress (2 week span)
            </h3>

            <div class="weight-box">
                <p class="chart-date">Date#1</p>
                <p class="chart-weight">185 lbs</p>
                <div class="outline">
                    <div class="level">
                        
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</template>

<script>

    

</script>

<style scoped>

.center-content
{
    width: 90%;
    position: relative; 
    padding: 20px;
    background: #fff;
    font-weight: bolder;
}

h3
{
    color: #000;
    text-transform: uppercase;
    letter-spacing: 2px;
}

.weight-box
{
    box-sizing: border-box;
    width: 100%;
    margin: 20px 0;
}

.chart-date
{
    color: #000;
    text-transform: uppercase;
    margin: 0 0 10px;
    padding: 0;
    letter-spacing: 1px;
}

.chart-weight
{
    float: right;
    position: relative;
    top: -25%
}

.outline
{
    background: #262626;
    padding: 4px;
    box-sizing: border-box;
    border: 1px solid #5b695d;
    border-radius: 0px;
}

.level
{
    background: #5b695d;
    height: 10px;
}

</style>