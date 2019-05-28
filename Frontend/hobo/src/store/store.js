import Vue from 'vue'
import Vuex from 'vuex'
import module from './module.js'
import searchmodule from './searchmodule.js'
import allProductsModule from './allProductsModule.js'
import product from './product.js'
import order from './order.js'
import category from './category.js'
import cart from './cart.js'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        module,
        searchmodule,
        allProductsModule,
        product,
        order,
        category,
        cart
    }
})