export default {
  parseUri(name) {
      const search = window.location.search;
      name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
      const regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
          results = regex.exec(search);
      return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
  }
}
