const os = require('os');

setInterval(() => {
  const { platform, arch, totalmem, freemem, release } = os;
  const totalRam = totalmem() / 1024 / 1024; //Convert bytes to mega bytes
  const freeRam = freemem() / 1024 / 1024; //Convert bytes to mega bytes
  const usageInPercents = (freeRam / totalRam) * 100;

  const stats = {
    OS: platform(),
    Release: release(),
    Arch: arch(),
    TotalRAM: parseInt(totalRam),
    FreeRAM: parseInt(freeRam),
    Usage: `${usageInPercents.toFixed(2)}%`,
  };
  console.clear();
  console.table(stats);
  exports.stats = stats;
}, 1000);
