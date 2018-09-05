<template lang="html">
  <div>
    <!-- <div class="page-bar" v-if="showPageBar">
      <ul class="page-breadcrumb">
        <li><a href="index.html">Home</a></li>
        <li>&nbsp;/&nbsp;</li>
        <li><span>Dashboard</span></li>
      </ul>
    </div> -->
    <h1 class="page-title" v-if="showTitle"> {{title}}</h1>

    <div class="portlet light bordered">
      <slot name="top-bar"></slot>
      <div class="portlet-title" v-show="showSearchBar">
        <div class="caption font-dark">
          <slot name="search-bar"></slot>
        <!-- <span class="caption-subject bold uppercase">{{title}}</span> -->
        </div>
        <div class="tools">
          <slot name="title-bar-tools"></slot>
        </div>
      </div>

      <div class="portlet-body">
        <div class="dataTables_wrapper no-footer">
          <div class="row">
            <div class="col-xs-12">
              <div class="dt-buttons">
                <slot name="buttons"></slot>
                <!-- <a class="dt-button buttons-print btn dark btn-outline" tabindex="0" aria-controls="sample_1" href="#"><span>Print</span></a>
                <a class="dt-button buttons-pdf buttons-html5 btn green btn-outline" tabindex="0" aria-controls="sample_1" href="#"><span>PDF</span></a>
                <a class="dt-button buttons-csv buttons-html5 btn purple btn-outline" tabindex="0" aria-controls="sample_1" href="#"><span>CSV</span></a> -->
              </div>
            </div>
          </div>
          <!-- <div class="row">
            <div class="col-xs-12 col-sm-12">
              <slot name="search-bar"></slot>
            </div>
          </div> -->

          <div class="row">
            <slot></slot>
          </div>
        </div>
        <div class="portlet-footer row" v-if="showFooter">
          <slot name="footer"></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'portlet',
  props: {
    title: String,
    showSearchBar: {
      type: Boolean,
      default: () => true
    },
    showPageBar: {
      type: Boolean,
      default: () => true
    },
    showTitle: {
      type: Boolean,
      default: () => true
    },
    showFooter: {
      type: Boolean,
      default: () => true
    },
    fullHeight: {
      type: Boolean,
      default: () => true
    }
  }
}
</script>

<style lang="css" scoped="scoped">
  .portlet-footer {
    padding: 10px 0;
    text-align: center;
    position: relative;
    bottom: 0px;
    left: 50%;
    width: 100%;
    margin-left: -50%;
  }
</style>
