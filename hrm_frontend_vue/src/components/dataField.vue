<template>
        <tr>
          <th v-for="(field,index) in fields" :key="'field'+index">
            <!-- 順番変更 -->
            <div class="th_inline">
                <div v-show="field!=='other'" class="order_button">
                    <button @click="changeOrder(field, 'asc')">
                        <i class="fa fa-caret-up" aria-hidden="true"></i>
                    </button>
                    <button @click="changeOrder(field, 'desc')">
                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </button>
                </div>
                <span id="field_name">
                    {{field_name[0][field]}}
                </span>
                <!-- 順番変更キャンセル -->
                <button v-show="field!=='other'" @click="orderClear()" class="order_clear">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
            </div>
          </th>
          <th></th>
        </tr>
</template>

<script>
export default {
    name: 'dataField',
    data () {
        return {
            order:null
            }
    },
    props: {
        fields: {
            type: Array
        },
        field_name: {
            type: Array
        }
    },
    methods: {
        changeOrder(field, order){
            this.$emit('changeOrderData', field, order)
        },
        orderClear(){
            this.$emit('orderClear')
        }
    }
    
}
</script>

<style lang="scss" scoped>
    button{
        border:none;
        background: transparent;
        font-size:12px;
        padding:0;
        &:hover{
            color:grey;
        }
    }


    th{
        border-width:0.5px;
        border-color:black;
        border-style:solid;
        height:40px;
        background: rgb(204, 203, 203);

        & .th_inline{
            @extend %display-flex;
            @extend %align-items-center;
            @extend %justify-content-center;
            position: relative;
            width:100%;
            height:100%;
        

            & .order_button{
                @extend %display-flex;
                @extend %flex-direction-column;
                position: absolute;
                left: 10px;

                
            }

            & .order_clear{
                background:transparent;
                border:none;
                padding:0;
                position: absolute;
                right: 10px;
            }
        }
    }
</style>