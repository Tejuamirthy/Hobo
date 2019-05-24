import Vue from 'vue'
import Vuex from 'vuex'
import module from './module.js'
import searchmodule from './searchmodule.js'
import allProductsModule from './allProductsModule.js'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        module,
        searchmodule,
        allProductsModule
    }
})