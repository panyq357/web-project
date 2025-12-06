let result = [];
let counter = 0;
const total = 5;


function outputResultWhenComplete() {
  if (counter == total) {
    for (let i=0; i<total; i++) {
      console.log(result[i]);
    }
  }
}

function asyncOpt(order, callback) {
  const ms = Math.random();
  setTimeout(
    ()=>{
      result[order] = order + " complete";
      counter++;
      callback()
    },
    ms
  );
}

for (let i=0; i<total; i++) {
  asyncOpt(i, outputResultWhenComplete);
}
