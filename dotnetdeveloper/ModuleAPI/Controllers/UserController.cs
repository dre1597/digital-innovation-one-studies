using Microsoft.AspNetCore.Mvc;

namespace ModuleAPI.Controllers;

[ApiController]
[Route("[controller]")] // api/user[]
public class UserController : ControllerBase
{
    [HttpGet("CurrentDate")]
    public IActionResult GetCurrentDate()
    {
        var date = new
        {
            Date = DateTime.Now.ToLongDateString(),
            Hour = DateTime.Now.ToShortTimeString()
        };

        return Ok(date);
    }

    [HttpGet("present/{name}")]
    public IActionResult GetByName(string name)
    {
        var message = $"Hello {name}";
        return Ok(new { message });
    }
}
