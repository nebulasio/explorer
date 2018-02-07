<style>
    /*
        https://vuejs.org/v2/examples/modal.html
        */

    .vue-modal {
        background-color: rgba(0, 0, 0, .3);
        display: flex;
        height: 100%;
        left: 0;
        position: fixed;
        top: 0;
        transition: opacity .3s ease;
        width: 100%;
    }

    .vue-modal .outer {
        background-color: white;
        box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
        margin: auto;
        transition: transform .3s ease;
    }

    .vue-modal .outer .close {
        text-align: right;
    }

    .vue-modal .outer .close>div {
        cursor: pointer;
        display: inline-block;
        font-size: 18px;
        line-height: 40px;
        text-align: center;
        vertical-align: top;
        width: 40px;
    }

    /*
        The following styles are auto-applied to elements with
        transition="modal" when their visibility is toggled by Vue.js.

        You can easily play with the modal transition by editing these styles.
        */

    .vue-modal.modal-enter {
        opacity: 0;
    }

    .vue-modal.modal-leave-active {
        opacity: 0;
    }

    .vue-modal.modal-enter .outer,
    .vue-modal.modal-leave-active .outer {
        transform: scale(1.1);
    }
</style>
<template>
    <transition name=modal>
        <div class=vue-modal>
            <div class=outer>
                <slot name=l1>
                    <div class=close>
                        <div class=comment v-on:click="$emit('close')">x</div>
                    </div>
                </slot>
                <slot name=l2></slot>
                <slot name=l3></slot>
                <slot name=l4></slot>
            </div>
        </div>
    </transition>
</template>